package com.test.COCONSULT.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class CandidatDetailsDTO {
    private int candidatId;
    private String candidatEmail;
    private String jobOpportTitle;
    private double finalMark;
    private String pdfFile;
}
