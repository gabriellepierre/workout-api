package org.com.dto.user_dto;

public class UpdateUserDto extends UpdateUserBaseDto {
    private String program;

    public UpdateUserDto(String pseudo, String password, String email, String program) {
        super(pseudo, password, email);
        this.program = program;
    }

    public UpdateUserDto() {
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
