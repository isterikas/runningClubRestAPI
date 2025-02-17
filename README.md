
# Clarifications

I have added an additional annotation @ContainsUser to ensure that every admin in the system is a user as well.

To avoid null values in UserResponseDTO, I have formatted it in a way for it to contain information on the role id and name. It was a personal, conscious and aesthetically-driven decision to do so. As a result, for the idea to work I had to create a RoleRepository and place PasswordEncoder in UserService.
