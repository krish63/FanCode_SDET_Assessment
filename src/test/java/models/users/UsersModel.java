package models.users;

public record UsersModel(int id,
                         String name,
                         String username,
                         String email,
                         Address address,
                         String phone,
                         String website,
                         Company company) {
}
