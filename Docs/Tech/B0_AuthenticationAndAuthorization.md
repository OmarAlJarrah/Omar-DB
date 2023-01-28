# Authentication and Authorization
## 1) Authentication
### How Authentication is handled?
Authentication is handled by Spring Boot Security.
```java
httpSecurity
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .userDetailsService(userDetailsService)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
```
### What is Spring Security
Spring Security is a Java-based security framework for securing Java-based applications. It provides a flexible and configurable security infrastructure for authentication, authorization, and secure access to resources.

#### Spring Security offers a variety of features, including:

1) **Authentication**: the process of verifying that a user is who they claim to be.
2) **Authorization**: the process of granting or denying access to specific resources or actions based on a user's identity and roles.
3) **Secure communication**: Spring Security provides support for secure communication via HTTPS and other protocols.
4) **Access control**: Spring Security provides a flexible framework for controlling access to resources, including support for fine-grained access control using annotations.

### How passwords are stored?
t registration process, Spring Security will take the password of the user, verify that it is a valid password, then encrypt the password using the BCrypt algorthm.

### What is the BCrypt algorithm?
The BCrypt algorithm is a key-derivation function that was specifically designed to be slow and computationally expensive, making it more difficult to crack hashed passwords through brute-force attacks.

### How does the BCrypt algorithm works?
The BCryptPasswordEncoder uses the following steps to encode a password:

1) A random salt is generated: This is a sequence of characters that is added to the password before it is hashed. The salt is unique for each encoded password, which makes the resulting hash different even if the same password is used multiple times.
2) The password and salt are concatenated: The salt and the plain text password are concatenated together to form the input for the hashing function.
3) The password is hashed: The input is passed through the BCrypt algorithm, which performs a series of computationally expensive operations on the input. The result of these operations is the hashed password.
4) The salt and hashed password are stored: The salt and the resulting hashed password are stored together in a database or other storage mechanism.
5) When verifying a password, the stored salt is concatenated with the entered password and the same hashing function is applied to the result, then the encoded password is compared with the stored one. If they match, the password is verified.

### How authentication process happens?
1) **Username and Password**: At first, and in any request, users should include their password and password in the request headers.
2) **Verification**: Spring Security will verify if the password matches the encrypted stored password.
3) **Token Retrieval**: An access token is retrieved, and is stored on user side as a cookie, later on it gets used for authentication instead of using username and password each time.

## 2) Authorization
### What roles are managed?
#### Admin
+ Read-Write permissions.
+ Adding, updating and removing users.
#### Normal Users
+ Read-Write permissions.

### How Permissions are Handled?
Permissions are handled by adding one more step of validation to the role of the user, if and only if the requested endpoint is marked as "Admin Access Only".
```java
httpSecurity
        .authorizeRequests()
        .antMatchers("*/users/*")
        .hasRole(Role.ROLE_ADMIN.role)
```
## User Details Service
### Definition
The UserDetailsService interface is used to retrieve user-related information. It is responsible for loading user-specific data for the purpose of authentication and authorization. 

The implementation of this interface is used by the authentication manager to validate the user credentials and to load the user's authorities. It provides a single method, loadUserByUsername, which takes a username as a parameter and returns an instance of org.springframework.security.core.userdetails.UserDetails, which contains the user's authentication and authorization information.

### Implementation
```java
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    JSONObject jsonObject = (JSONObject) reader.getUserJsonObject(username);
    if (jsonObject.getJSONObject(username).isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    User user = fromJson(
        jsonObject.getJSONObject(username).toString(),
        User.class
    );

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        List.of(new SimpleGrantedAuthority(user.getRole().role))
    );
  }
```
