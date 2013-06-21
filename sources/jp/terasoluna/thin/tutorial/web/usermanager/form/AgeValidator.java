/*
 * Copyright (c) 2007 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.terasoluna.thin.tutorial.web.usermanager.form;

import java.text.SimpleDateFormat;
import jp.terasoluna.fw.util.DateUtil;
import jp.terasoluna.fw.web.struts.form.MultiFieldValidator;

/**
 * ���͂��ꂽ�N��ƁA���N�����̑��֓��̓`�F�b�N���s���B
 * 
 */
public class AgeValidator implements MultiFieldValidator {

    /**
     * ���͂��ꂽ�N��ƁA���N�����̑��֓��̓`�F�b�N���s���B
     * 
     * @param birth ���N����
     * @param fields �֘A������̓t�B�[���h�l
     * @return �`�F�b�N����
     */
    public boolean validate(String birth, String[] fields) {
        String age = fields[0];

        if (birth == null || "".equals(birth)) {
            return true;
        }

        return checkBirthAndAge(birth, Integer.parseInt(age));
    }

    /**
     * ���͂��ꂽ���N��������v�Z�����N��Ɠ��͂��ꂽ�N����r����B
     * ����̏ꍇ��true��ԋp�A�قȂ��Ă���ꍇ�́Afalse��ԋp����B
     * 
     * @param birth ���͂��ꂽ�a����
     * @param age ���͂��ꂽ�N��
     * @return ��r����
     */
    private boolean checkBirthAndAge(String birth, int age) {

        //���ݓ��t�̎擾
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String temp = format.format(DateUtil.getSystemTime());

        //�N������z��ɕϊ��B
        String[] old = birth.split("/");
        String[] now = temp.split("/");

        //�P���Ɍ��݂̔N����A���͂��ꂽ�N�������B
        int minus = Integer.parseInt(now[0]) - Integer.parseInt(old[0]);

        //���͂��ꂽ�������݂̌��ȉ��̏ꍇ�A����
        //���͂��ꂽ�������݂̓������������ꍇ�́A�}�C�i�X�P����B
        if ((Integer.parseInt(now[1]) < Integer.parseInt(old[1]))
         || (Integer.parseInt(now[1]) == Integer.parseInt(old[1])
         && Integer.parseInt(now[2]) < Integer.parseInt(old[2]))) {
            minus--;
        }

        //���͂��ꂽ�N��Ɣ�r����B
        if (age == minus) {
            return true;
        }
        return false;
    }
}