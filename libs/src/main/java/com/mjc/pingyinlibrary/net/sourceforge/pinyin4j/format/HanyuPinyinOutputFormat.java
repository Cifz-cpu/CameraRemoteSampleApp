/**
 * This file is part of pinyin4j (http://sourceforge.net/projects/pinyin4j/) 
 * and distributed under GNU GENERAL PUBLIC LICENSE (GPL).
 * 
 * pinyin4j is free software; you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation; either version 2 of the License, or 
 * (at your option) any later version. 
 * 
 * pinyin4j is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 * GNU General Public License for more details. 
 * 
 * You should have received a copy of the GNU General Public License 
 * along with pinyin4j.
 */

package com.mjc.pingyinlibrary.net.sourceforge.pinyin4j.format;

import net.sourceforge.pinyin4j.format.*;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * This classes define how the Hanyu Pinyin should be outputted.
 * 
 * <p>
 * The output feature includes:
 * <ul>
 * <li>Output format of character 'ü';
 * <li>Output format of Chinese tones;
 * <li>Cases of letters in outputted string
 * </ul>
 * 
 * <p>
 * Default values of these features are listed below:
 * 
 * <p>
 * HanyuPinyinVCharType := WITH_U_AND_COLON <br>
 * HanyuPinyinCaseType := LOWERCASE <br>
 * HanyuPinyinToneType := WITH_TONE_NUMBER <br>
 * 
 * <p>
 * <b>Some combinations of output format options are meaningless. For example,
 * WITH_TONE_MARK and WITH_U_AND_COLON.</b>
 * 
 * <p>
 * The combination of different output format options are listed below. For
 * example, '吕'
 * 
 * <table border="1">
 * <tr>
 * <th colspan="4"> LOWERCASE </th>
 * </tr>
 * <tr>
 * <th>Combination</th>
 * <th>WITH_U_AND_COLON</th>
 * <th>WITH_V</th>
 * <th>WITH_U_UNICODE</th>
 * </tr>
 * <tr>
 * <th>WITH_TONE_NUMBER</th>
 * <td>lu:3</td>
 * <td>lv3</td>
 * <td>lü3</td>
 * </tr>
 * <tr>
 * <th>WITHOUT_TONE</th>
 * <td>lu:</td>
 * <td>lv</td>
 * <td>lü</td>
 * </tr>
 * <tr>
 * <th>WITH_TONE_MARK</th>
 * <td><font color="red">throw exception</font></td>
 * <td><font color="red">throw exception</font></td>
 * <td>lǚ</td>
 * </tr>
 * </table>
 * 
 * <table border="1">
 * <tr>
 * <th colspan="4"> UPPERCASE </th>
 * </tr>
 * <tr>
 * <th>Combination</th>
 * <th>WITH_U_AND_COLON</th>
 * <th>WITH_V</th>
 * <th>WITH_U_UNICODE</th>
 * </tr>
 * <tr>
 * <th>WITH_TONE_NUMBER</th>
 * <td>LU:3</td>
 * <td>LV3</td>
 * <td>LÜ3</td>
 * </tr>
 * <tr>
 * <th>WITHOUT_TONE</th>
 * <td>LU:</td>
 * <td>LV</td>
 * <td>LÜ</td>
 * </tr>
 * <tr>
 * <th>WITH_TONE_MARK</th>
 * <td><font color="red">throw exception</font></td>
 * <td><font color="red">throw exception</font></td>
 * <td>LǙ</td>
 * </tr>
 * </table>
 * 
 * @see net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
 * @see net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
 * @see net.sourceforge.pinyin4j.format.HanyuPinyinToneType
 * 
 * @author Li Min (xmlerlimin@gmail.com)
 * 
 */
final public class HanyuPinyinOutputFormat
{

    public HanyuPinyinOutputFormat()
    {
        restoreDefault();
    }

    /**
     * Restore default variable values for this class
     * 
     * Default values are listed below:
     * 
     * <p>
     * HanyuPinyinVCharType := WITH_U_AND_COLON <br>
     * HanyuPinyinCaseType := LOWERCASE <br>
     * HanyuPinyinToneType := WITH_TONE_NUMBER <br>
     */
    public void restoreDefault()
    {
        vCharType = net.sourceforge.pinyin4j.format.HanyuPinyinVCharType.WITH_U_AND_COLON;
        caseType = net.sourceforge.pinyin4j.format.HanyuPinyinCaseType.LOWERCASE;
        toneType = net.sourceforge.pinyin4j.format.HanyuPinyinToneType.WITH_TONE_NUMBER;
    }

    /**
     * Returns the output cases of Hanyu Pinyin characters
     * 
     * @see net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
     */
    public net.sourceforge.pinyin4j.format.HanyuPinyinCaseType getCaseType()
    {
        return caseType;
    }

    /**
     * Define the output cases of Hanyu Pinyin characters
     * 
     * @param caseType
     *            the output cases of Hanyu Pinyin characters
     * 
     * @see net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
     */
    public void setCaseType(net.sourceforge.pinyin4j.format.HanyuPinyinCaseType caseType)
    {
        this.caseType = caseType;
    }

    /**
     * Returns the output format of Chinese tones
     * 
     * @see net.sourceforge.pinyin4j.format.HanyuPinyinToneType
     */
    public net.sourceforge.pinyin4j.format.HanyuPinyinToneType getToneType()
    {
        return toneType;
    }

    /**
     * Define the output format of Chinese tones
     * 
     * @param toneType
     *            the output format of Chinese tones
     * 
     * @see net.sourceforge.pinyin4j.format.HanyuPinyinToneType
     */
    public void setToneType(net.sourceforge.pinyin4j.format.HanyuPinyinToneType toneType)
    {
        this.toneType = toneType;
    }

    /**
     * Returns output format of character 'ü'
     * 
     * @see net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
     */
    public net.sourceforge.pinyin4j.format.HanyuPinyinVCharType getVCharType()
    {
        return vCharType;
    }

    /**
     * Define the output format of character 'ü'
     * 
     * @param charType
     *            the output format of character 'ü'
     * 
     * @see net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
     */
    public void setVCharType(net.sourceforge.pinyin4j.format.HanyuPinyinVCharType charType)
    {
        vCharType = charType;
    }

    private net.sourceforge.pinyin4j.format.HanyuPinyinVCharType vCharType;

    private HanyuPinyinCaseType caseType;

    private HanyuPinyinToneType toneType;

}
