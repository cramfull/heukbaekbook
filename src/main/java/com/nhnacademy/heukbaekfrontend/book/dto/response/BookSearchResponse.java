package com.nhnacademy.heukbaekfrontend.book.dto.response;

import java.time.LocalDate;

public record BookSearchResponse(
        String title,
        String cover,
        String description,
        String category,
        String author,
        String publisher,
        LocalDate pubDate,
        String isbn,
        int standardPrice,
        int salesPrice
) {}
