package com.clanone.onedayclan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
@ToString
public class OnedayclanResponse<T> {
    private Header header;
    private T result;

    public static <E> OnedayclanResponse<E> of(E result) {
        return OnedayclanResponse.<E>builder()
                .header(Header.builder()
                        .isSuccessful(true)
                        .statusCode(HttpStatus.OK)
                        .resultMessage("")
                        .build())
                .result(result)
                .build();
    }

    public static OnedayclanResponse<Void> success(){
        return OnedayclanResponse.<Void>builder()
                .header(
                        Header.builder()
                                .isSuccessful(true)
                                .statusCode(HttpStatus.OK)
                                .resultMessage("")
                                .build()
                )
                .build();
    }

    public static <L> OnedayclanResponse<List<L>> of(List<L> result) {
        return OnedayclanResponse.<List<L>>builder()
                .header(
                        Header.builder()
                                .isSuccessful(true)
                                .statusCode(HttpStatus.OK)
                                .resultMessage("")
                                .build()
                )
                .result(result)
                .build();
    }

    public static <P> OnedayclanResponse<PagingResult<P>> of(List<P> result, int pageNumber, long totalElement) {
        PagingResult<P> pagingResult = PagingResult.<P>builder()
                .totalElements(totalElement)
                .pageNumber(pageNumber)
                .content(result)
                .build();
        return OnedayclanResponse.<PagingResult<P>>builder()
                .header(
                        Header.builder()
                                .isSuccessful(true)
                                .statusCode(HttpStatus.OK)
                                .resultMessage("")
                                .build()
                )
                .result(pagingResult)
                .build();
    }

    public static OnedayclanResponse<Void> ofCodeMessage(ErrorCode errorCode, String message) {
        return OnedayclanResponse.<Void>builder()
                .header(
                        Header.builder()
                                .isSuccessful(false)
                                .statusCode(errorCode.getHttpStatus())
                                .resultMessage(message)
                                .build()
                )
                .build();
    }

    public static OnedayclanResponse<Void> ofCodeMessage(HttpStatus httpStatus, String message) {
        return OnedayclanResponse.<Void>builder()
                .header(
                        Header.builder()
                                .isSuccessful(false)
                                .statusCode(httpStatus)
                                .resultMessage(message)
                                .build()
                )
                .build();
    }

    @Builder
    @ToString
    public static class Header {
        private boolean isSuccessful;
        private HttpStatus statusCode;
        private String resultMessage;

        @JsonProperty(value = "isSuccessful")
        public boolean isSuccessful() {
            return isSuccessful;
        }

        public int getStatusCode() {
            return statusCode.value();
        }

        public String getResultMessage() {
            return resultMessage;
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class PagingResult<P> {
        private long totalElements;
        private int pageNumber;
        private List<P> content;
    }
}
