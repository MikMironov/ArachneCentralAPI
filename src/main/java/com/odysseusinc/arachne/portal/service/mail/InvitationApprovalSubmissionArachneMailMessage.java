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
 * Created: June 22, 2017
 *
 */

package com.odysseusinc.arachne.portal.service.mail;

import com.odysseusinc.arachne.portal.model.Submission;
import com.odysseusinc.arachne.portal.model.SubmissionStatus;
import com.odysseusinc.arachne.portal.model.User;

public class InvitationApprovalSubmissionArachneMailMessage extends InvitationArachneMailMessage {



    private final Submission submission;

    public InvitationApprovalSubmissionArachneMailMessage(String portalUrl, User user, Submission submission) {
        super(portalUrl, user, submission.getToken());

        this.submission = submission;

        parameters.put("userId", user.getId());
        parameters.put("analysesUrl", portalUrl+"/analysis-execution/analyses/"+submission.getAnalysis().getId());
        parameters.put("submissionId", submission.getId());
        parameters.put("userFirstName", user.getFirstname());
    }


    @Override
    protected String getSubject() {
        return submission.getStatus().isFinished() ? "Arachne request to publish a submission" : "Arachne request to execute a submission";
    }

    @Override
    protected String getTemplate() {

        if (submission.getStatus().isFinished()) {
            return "mail/invitation_approval_publish_submission";
        } else {
            return "mail/invitation_approval_execute_submission";
        }

    }

}
