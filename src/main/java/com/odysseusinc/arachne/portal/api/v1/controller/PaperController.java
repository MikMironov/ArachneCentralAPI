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
 * Created: July 14, 2017
 *
 */

package com.odysseusinc.arachne.portal.api.v1.controller;

import com.odysseusinc.arachne.portal.api.v1.dto.CreatePaperDTO;
import com.odysseusinc.arachne.portal.api.v1.dto.PaperDTO;
import com.odysseusinc.arachne.portal.api.v1.dto.ShortPaperDTO;
import com.odysseusinc.arachne.portal.api.v1.dto.UpdatePaperDTO;
import com.odysseusinc.arachne.portal.model.Paper;
import com.odysseusinc.arachne.portal.model.search.PaperSearch;
import com.odysseusinc.arachne.portal.service.BasePaperService;
import com.odysseusinc.arachne.portal.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RequestMapping(path = "/api/v1/papers")
@RestController
public class PaperController extends BasePaperController
        <Paper,
        PaperSearch,
        UpdatePaperDTO,
        ShortPaperDTO,
        PaperDTO,
        CreatePaperDTO> {

    public PaperController(
            BasePaperService<Paper, PaperSearch> paperService,
            GenericConversionService conversionService,
            FileService fileService) {

        super(paperService, conversionService, fileService);
    }

    @Override
    protected ShortPaperDTO convertPaperToShortPaperDTO(Paper paper) {

        return conversionService.convert(paper, ShortPaperDTO.class);
    }

    @Override
    protected PaperDTO convertPaperToPaperDTO(Paper paper) {

        return conversionService.convert(paper, PaperDTO.class);
    }

    @Override
    protected Paper convertUpdatePaperDtoToPaper(UpdatePaperDTO updatePaperDTO) {

        return conversionService.convert(updatePaperDTO, Paper.class);
    }
}
