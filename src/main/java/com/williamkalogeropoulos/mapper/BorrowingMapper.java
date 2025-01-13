package com.williamkalogeropoulos.mapper;

import com.williamkalogeropoulos.dto.BorrowingDTO;
import com.williamkalogeropoulos.entity.Borrowing;

public class BorrowingMapper {
    public static BorrowingDTO toDTO(Borrowing borrowing) {
        BorrowingDTO dto = new BorrowingDTO();
        dto.setId(borrowing.getId());
        dto.setBookId(borrowing.getBook().getId());
        dto.setBookTitle(borrowing.getBook().getTitle());
        dto.setBookAuthor(borrowing.getBook().getAuthor());
        dto.setBookIsbn(borrowing.getBook().getIsbn());
        dto.setBorrowDate(borrowing.getBorrowDate());
        dto.setReturnDate(borrowing.getReturnDate());
        dto.setOverdueCharges(borrowing.getOverdueCharges());
        dto.setActive(borrowing.getActive());
        return dto;
    }
}
