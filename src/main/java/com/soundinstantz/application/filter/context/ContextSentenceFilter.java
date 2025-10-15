package com.soundinstantz.application.filter.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContextSentenceFilter {
    private Long analysisContextId;
    private String englishText;
    private String vietnameseText;
}
