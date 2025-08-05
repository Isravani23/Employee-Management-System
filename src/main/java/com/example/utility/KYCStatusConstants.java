package com.example.utility;

import java.util.List;

public class KYCStatusConstants {

    public static final String PENDING = "PENDING";
    public static final String APPROVED = "APPROVED";
    public static final String REJECTED = "REJECTED";

    public static final List<String> VALID_STATUSES = List.of(PENDING, APPROVED, REJECTED);
}

