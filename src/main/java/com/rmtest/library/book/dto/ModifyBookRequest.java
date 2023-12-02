package com.rmtest.library.book.dto;

import com.rmtest.library.book.dto.BookRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ModifyBookRequest extends BookRequest {
    private Integer id;

}
