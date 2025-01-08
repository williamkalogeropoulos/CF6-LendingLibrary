package com.williamkalogeropoulos.mapper;

import com.williamkalogeropoulos.dto.BorrowingDTO;
import com.williamkalogeropoulos.entity.Borrowing;

public class BorrowingMapper {
    public static BorrowingDTO toDTO(Borrowing borrowing) {
        return new BorrowingDTO(
                borrowing.getId(),
                borrowing.getUser() != null ? borrowing.getUser().getUsername() : "Unknown",
                borrowing.getBook() != null ? borrowing.getBook().getId() : null,
                borrowing.getBorrowDate(),
                borrowing.getReturnDate(),
                borrowing.getOverdueCharges()
        );
    }
}
