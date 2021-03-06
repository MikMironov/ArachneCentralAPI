/**
 *
 * Copyright 2017 Observational Health Data Sciences and Informatics
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Company: Odysseus Data Services, Inc.
 * Product Owner/Architecture: Gregory Klebanov
 * Authors: Pavel Grafkin, Alexandr Ryabokon, Vitaly Koulakov, Anton Gackovka, Maria Pozhidaeva, Mikhail Mironov
 * Created: January 13, 2017
 *
 */

package com.odysseusinc.arachne.portal.api.v1.dto;

import java.util.LinkedList;
import java.util.List;

public class AnalysisDTO extends BaseAnalysisDTO {

    public AnalysisDTO() {

    }

    public AnalysisDTO(BaseAnalysisDTO other) {

        super(other);
    }

    private List<AnalysisFileDTO> files = new LinkedList<>();
    private List<SubmissionGroupDTO> submissionGroup = new LinkedList<>();
    private Boolean locked;

    public List<AnalysisFileDTO> getFiles() {

        return files;
    }

    public void setFiles(List<AnalysisFileDTO> files) {

        this.files = files;
    }

    public List<SubmissionGroupDTO> getSubmissionGroup() {

        return submissionGroup;
    }

    public void setSubmissionGroup(List<SubmissionGroupDTO> submissionGroup) {

        this.submissionGroup = submissionGroup;
    }

    public Boolean getLocked() {

        return locked;
    }

    public void setLocked(Boolean locked) {

        this.locked = locked;
    }
}
