# Data Management
## Data Persistence 
Data is persistence on hard-disk, and follows a specific structure/pattern which helps that database access all data easily.
```text
|-- DB
    |-- TABLES
    |   `-- test
    |       `-- 7954bc95-46e3-4d5f-8cf1-12482e618cc5
    |
    `-- USERS
        |-- 72ff1b4e-78a4-4845-8e8e-3a43ddc5c286
        |
        `-- 7954bc95-46e3-4d5f-8cf1-12482e618cc5
```

## Documents Structure
Documents are broken using a divide and conquer approach into multiple smaller documents, to support GraphQL like queries on filesystem levels.

### Write Algorithm
The algorithm I designed in-order to achieve this structure when writing json documents works as follows:

1) Function takes 2 params, the current path we are working on, and the data we want to write to disk, wrapped inside a ```JSONObject```.
```java
private void writeJsonObjectToFiles(String currentPath, JSONObject object) throws IOException {
  // -- CODE --
}
```

2) If the current path/directory is missing, create it.
```java
// -- CODE -- 
com.omar.util.impl.FileUtils.createDirectoryIfAbsent(currentPath);
// -- CODE --
```

3) Traverse the keys available in the ```JSONObject``` on the first level (depth of 1)
```java
// -- CODE --
Iterator<String> keys = object.keys();
while (keys.hasNext()){
    String key=keys.next();
    Object value=object.get(key);
}
// -- CODE --
```

4) If the value is another ```JSONObject```, we will calculate the path value to write it inside the current object being the directory we writing into, and we will do a recursive call to write the child object.
```java
if (value instanceof JSONObject) {
    String nextPath = (currentPath.endsWith("/") ? currentPath : currentPath + File.separator) + key;
    FileUtils.createDirectoryIfAbsent(nextPath);

    writeJsonObjectToFiles(nextPath, object.getJSONObject(key));
    continue;
}
```

5) If the value is not another ```JSONObject```, then it is a field of key-value type, in other words, it's a leaf node, then we will write it into the file system as a document.
```java
// -- CODE --
String filePath = ((currentPath.endsWith("/") ? currentPath : currentPath + File.separator) + key);
filePath = filePath.endsWith(".json") ? filePath : filePath + FileExtension.JSON.value;

FileUtils.createFileIfAbsent(filePath);

var writer = new BufferedWriter(new FileWriter(filePath));
JSONObject jsonObject = new JSONObject();
jsonObject.put(key, object.get(key));
writer.write(jsonObject.toString());
// -- CODE --
```
### Sample Results
+ **Input**:
```json
{
    "7954bc95-46e3-4d5f-8cf1-12482e618cc5": {
        "career": {
            "XY": 2,
            "job": "eng"
        },
        "array": [
            1,
            2
        ],
        "name": {
            "test": {
                "device": "mac"
            },
            "last": "Jarrah",
            "first": "Omar"
        },
        "age": 22
    }
}
```
+ **Output**
```text
.
`-- 7954bc95-46e3-4d5f-8cf1-12482e618cc5
    |-- career
    |   |-- job.json
    |   `-- xy.json
    |-- name
    |   |-- test
    |   |   `-- device.json
    |   |-- first.json
    |   `-- last.json
    |-- age.json
    `-- array.json
```

### Read Algorithm
I have designed 2 algorithms to read data, one that reads the whole document (gathers all files in a directory, and merges them into their original form), the other does the same thing, but using a query ```JSONObject```, which tells yoy to parse only some specific fields of the document (partial parse).

We will cover the later algorithm as it's built on top of the first one.

1) Function takes 2 params, the current path (as a ```File``` object) we are working on, and the query data wrapped inside a ```JSONObject```.
```java
public Object partialParse(File file, JSONObject query) {
    // -- CODE --
}
```

2) If the query tells us to parse the whole document/file/object we are at, we will use the parse function of the first algorithm, which parses all the files in the current path.
```java
private boolean shouldUseParse(JSONObject query, String currentKey) {
    return query.get(currentKey) instanceof Boolean && query.getBoolean(currentKey);
}
```
```java
if (shouldUseParse(query, fileName)) {
      return parser.parse(file);
}
```

3) If the query tells us to skip the current document/file/object we are at, we will skip it.
```java
if (query.get(fileName) instanceof Boolean && !query.getBoolean(fileName)) {
      return result;
}

if (!file.exists() || query.isEmpty()) {
      return result;
}
```

4) If we reached this step, this means we want to parse all or part of the current document. If the current path represents a single json document (key-value field), we will parse it whole using the normal parser.
```java
result = FileUtils.readJsonFile(file);
```

5) If the current path represents a directory (document with sub documents), we will use recursion in a DFS style to parse the document.

```java
JSONObject nextQuery = query.getJSONObject(fileName);
JSONObject childJsonObject = new JSONObject();
for (String child : Objects.requireNonNull(file.list())) {
String childFilePath = new StringJoiner("/")
    .add(file.getAbsolutePath())
    .add(child)
    .toString();

File childFile = new File(childFilePath);
String childFileName = FilenameUtils.getBaseName(childFile.getName());
JSONObject currentJsonObject;

if (nextQuery.isNull(childFileName)) {
  continue;
}
if (shouldUseParse(nextQuery, childFileName)) {
  currentJsonObject = (JSONObject) parser.parse(childFile);
  childJsonObject.put(childFileName, currentJsonObject.get(childFileName));
  continue;
} else {
  currentJsonObject = (JSONObject) partialParse(childFile.getAbsoluteFile(), query.getJSONObject(fileName));
}

Iterator<String> keys = currentJsonObject.keys();

while (keys.hasNext()) {
  String key = keys.next();
  if (!nextQuery.isNull(key)) {
    childJsonObject.put(key, currentJsonObject.get(key));
  }
}
}

result.put(fileName, childJsonObject);

```

# Files Access in Filesystem
Files are stored in a Linux environment with ```btrfs``` filesystem. This helps us to get into anyfile we want, given the path to that file in a worst case of ```O(log n)```.

## ```btrfs``` Filesystem
Btrfs (B-tree file system) is a modern, advanced file system that provides several features to improve the performance of file access. Some of the ways Btrfs improves file access performance include:

### B-tree data structure
Btrfs uses a B-tree data structure to organize and access files on disk. B-trees are designed to be efficient for both small and large file systems, and they allow for fast lookups and insertions of files.

### Caching
Btrfs uses caching mechanisms to speed up file access. The file system cache stores recently accessed data in memory, which allows for faster access to files. Btrfs also uses readahead, which anticipates the data that will be needed next, and loads it into the cache before it is requested.

### Compression
Btrfs supports transparent compression of files, which reduces the amount of disk space used and can also improve file access performance.

### Snapshots
Btrfs supports the ability to take snapshots of the file system, which allows for quick access to previous versions of files. This can be very useful in situations where a file was accidentally deleted or modified and needs to be restored.

### Data deduplication
Btrfs can detect and remove duplicate data, which can save space and speed up file access.

### Subvolumes and RAID
Btrfs allows for the creation of subvolumes, which are separate file systems within the main file system. This allows for better organization of data, and also allows for the use of RAID (redundant array of independent disks) which can improve performance and provide data redundancy.
