package com.oasis.taskmanagementapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedTaskResource implements Serializable {

    @Serial
    private static final long serialVersionUID = -89107205554317785L;

    @NotNull(message = "content must not be null")
    @JsonProperty("content")
    private List<TaskResource> taskResources;

    @NotNull(message = "current_page must have a value")
    @JsonProperty("current_page")
    private Integer currentPage;

    @NotNull(message = "total_pages must have a value")
    @JsonProperty("total_pages")
    private Integer totalPages;

    @NotNull(message = "total_elements must have a value")
    @JsonProperty("total_elements")
    private Long totalElements;
}
