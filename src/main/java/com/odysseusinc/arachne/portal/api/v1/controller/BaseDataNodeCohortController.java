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
 * Created: September 18, 2017
 *
 */

package com.odysseusinc.arachne.portal.api.v1.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.odysseusinc.arachne.commons.api.v1.dto.CommonAnalysisType;
import com.odysseusinc.arachne.commons.api.v1.dto.CommonCohortShortDTO;
import com.odysseusinc.arachne.portal.exception.NotExistException;
import com.odysseusinc.arachne.portal.model.DataNode;
import com.odysseusinc.arachne.portal.service.BaseDataNodeService;
import com.odysseusinc.arachne.portal.service.messaging.BaseDataNodeMessageService;
import io.swagger.annotations.Api;
import java.util.List;
import javax.jms.JMSException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public abstract class BaseDataNodeCohortController<DN extends DataNode> extends BaseController {
    private final BaseDataNodeService<DN> baseDataNodeService;

    private final BaseDataNodeMessageService<DN> dataNodeMessageService;

    public BaseDataNodeCohortController(BaseDataNodeService<DN> baseDataNodeService, BaseDataNodeMessageService<DN> dataNodeMessageService) {

        this.baseDataNodeService = baseDataNodeService;
        this.dataNodeMessageService = dataNodeMessageService;
    }

    /**
     * Returns list of cohorts defined in Atlas connected to the Data node
     * (for Central's UI)
     */
    @RequestMapping(
            value = "/api/v1/data-nodes/{dataNodeUuid}/cohorts",
            method = GET
    )
    public List<CommonCohortShortDTO> listCohorts(
            @PathVariable("dataNodeUuid") String dataNodeUuid
    ) throws JMSException, NotExistException {

        DN dataNode = baseDataNodeService.getBySid(dataNodeUuid);
        return dataNodeMessageService.getDataList(dataNode, CommonAnalysisType.COHORT);
    }

}
