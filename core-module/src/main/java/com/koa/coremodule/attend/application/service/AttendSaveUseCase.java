package com.koa.coremodule.attend.application.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.koa.coremodule.attend.domain.entity.Attend;
import com.koa.coremodule.attend.domain.service.AttendQueryService;
import com.koa.coremodule.attend.domain.service.AttendSaveService;
import com.koa.coremodule.member.domain.entity.Member;
import com.koa.coremodule.member.domain.service.MemberQueryService;
import com.koa.coremodule.member.domain.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendSaveUseCase {

    private final MemberUtils memberUtils;
    private final AttendSaveService attendSaveService;
    private final AttendQueryService attendQueryService;
    private final MemberQueryService memberQueryService;

    public BufferedImage generateQRCodeImage(String text, int width, int height)
            throws WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        return bufferedImage;
    }

    public Long saveAttend(Long curriculumId) {

        Member memberRequest = memberUtils.getAccessMember();
        Attend attend = attendSaveService.saveAttend(curriculumId, memberRequest.getId());

        return attend.getId();
    }

    public void saveAllAbsent(Long curriculumId) {

        List<Member> memberList = memberQueryService.findAllMember();

        for (Member m : memberList) {

            Attend attendExist = attendQueryService.findAttendByMemberId(m.getId(), curriculumId);
            if (attendExist == null) {
                Attend attend = attendSaveService.saveAttendForLate(curriculumId, m.getId());
            }
        }

    }

}
