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

package com.odysseusinc.arachne.portal.service.submission;

import com.odysseusinc.arachne.portal.api.v1.dto.ApproveDTO;
import com.odysseusinc.arachne.portal.exception.NoExecutableFileException;
import com.odysseusinc.arachne.portal.exception.NotExistException;
import com.odysseusinc.arachne.portal.exception.PermissionDeniedException;
import com.odysseusinc.arachne.portal.exception.ValidationException;
import com.odysseusinc.arachne.portal.model.Analysis;
import com.odysseusinc.arachne.portal.model.ResultFile;
import com.odysseusinc.arachne.portal.model.Submission;
import com.odysseusinc.arachne.portal.model.SubmissionFile;
import com.odysseusinc.arachne.portal.model.SubmissionGroup;
import com.odysseusinc.arachne.portal.model.SubmissionStatus;
import com.odysseusinc.arachne.portal.model.SubmissionStatusHistoryElement;
import com.odysseusinc.arachne.portal.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

public interface BaseSubmissionService<T extends Submission, A extends Analysis> {
    T approveSubmissionResult(Long submissionId, ApproveDTO approveDTO, User user);

    T approveSubmission(Long submissionId, Boolean isApproved, String comment, User user)
            throws IOException, NotExistException;

    T createSubmission(User user, A analysis, Long datasourceId,
                                SubmissionGroup submissionGroup)
            throws NotExistException, IOException;

    @PreAuthorize("hasPermission(#analysis, "
            + "T(com.odysseusinc.arachne.portal.security.ArachnePermission).CREATE_SUBMISSION)")
    SubmissionGroup createSubmissionGroup(User user, Analysis analysis) throws IOException, NoExecutableFileException;

    void deleteSubmissionInsight(Long submissionId) throws NotExistException;

    void tryDeleteSubmissionInsight(Long submissionInsightId);

    @PreAuthorize("hasPermission(#submissionId, 'Submission', "
            + "T(com.odysseusinc.arachne.portal.security.ArachnePermission).APPROVE_SUBMISSION)")
    boolean deleteSubmissionResultFile(Long submissionId, String fileUuid)
            throws NotExistException, ValidationException;

    void deleteSubmissionResultFile(ResultFile resultFile);

    T getSubmissionByIdAndToken(Long id, String token) throws NotExistException;

    T saveSubmission(T submission);

    T saveSubmissionAndFlush(T submission);

    T moveSubmissionToNewStatus(T submission, SubmissionStatus status, User user, String comment);

    T changeSubmissionState(Long id, String status);

    T getSubmissionById(Long id) throws NotExistException;

    T getSubmissionByIdAndStatus(Long id, SubmissionStatus status) throws NotExistException;

    T getSubmissionByIdAndUpdatePasswordAndStatus(Long id, String updatePassword, List<SubmissionStatus> status)
            throws NotExistException;

    void notifyOwnersAboutNewSubmission(T submission);

    void notifyOwnersAboutSubmissionUpdateViaSocket(T submission);

    ResultFile uploadResultsByDataOwner(Long submissionId, MultipartFile file) throws NotExistException, IOException;

    @PreAuthorize("hasPermission(#submissionId, 'Submission', "
            + "T(com.odysseusinc.arachne.portal.security.ArachnePermission).ACCESS_STUDY)")
    List<ResultFile> getResultFiles(User user, Long submissionId) throws PermissionDeniedException;

    @PreAuthorize("hasPermission(#analysisId,  'Analysis', "
            + "T(com.odysseusinc.arachne.portal.security.ArachnePermission).ACCESS_STUDY)")
    ResultFile getResultFile(User user, Long analysisId, String uuid) throws PermissionDeniedException;

    void getSubmissionResultAllFiles(User user, Long analysisId, Long submissionId, String archiveName, OutputStream os)
            throws IOException, PermissionDeniedException;

    Path getSubmissionArchiveChunk(Long id, String updatePassword, String fileName) throws FileNotFoundException;

    void getSubmissionAllFiles(Long submissionGroupId, String archiveName, OutputStream os) throws IOException;

    List<SubmissionStatusHistoryElement> getSubmissionStatusHistory(Long analysisId, Long submissionId);

    List<SubmissionFile> getSubmissionFiles(Long submissionGroupId);

    SubmissionFile getSubmissionFile(Long submissionGroupId, String uuid);

    SubmissionGroup getSubmissionGroupById(Long id) throws NotExistException;

    void deleteSubmissionStatusHistory(List<SubmissionStatusHistoryElement> statusHistory);

    void deleteSubmissions(List<T> submission);

    void deleteSubmissionGroups(List<SubmissionGroup> groups);
}
