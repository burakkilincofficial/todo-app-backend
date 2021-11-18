package com.bk.todoappbackend.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllUserResponse {
    private Integer todoCount;
    private List<UserResponse> data;
}
