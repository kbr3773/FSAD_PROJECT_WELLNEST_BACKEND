package com.wellnest.api.dto;

public record AdminStatsResponse(
    long totalStudents,
    long totalResources,
    long activePrograms,
    long supportRequests,
    long resourceViews,
    long programEnrollments
) {
}
