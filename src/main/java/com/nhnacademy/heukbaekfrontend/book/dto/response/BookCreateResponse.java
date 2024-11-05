package com.nhnacademy.heukbaekfrontend.book.dto.response;

import java.util.List;

public record BookCreateResponse(
        String title,
        String index,
        String description,
        String publication,
        String isbn,
        boolean isPackable,
        int stock,
        int standardPrice,
        float discountRate,
        String publisher,
        List<String> categories,
        List<String> authors
) {}