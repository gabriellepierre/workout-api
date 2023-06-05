package org.com.dto.user_dto;

public class UpdateUserBaseDto {
    private String pseudo;
    private String password;
    private String email;

    public UpdateUserBaseDto(String pseudo, String password, String email) {
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
    }

    public UpdateUserBaseDto() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
