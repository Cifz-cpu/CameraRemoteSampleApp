package com.example.sony.cameraremote.contant;

/**
 * desc:命令
 * Author:cifz
 * time:2018/5/4 9:22
 * e_mail:wangzhen1798@gmail.com
 */

public class Command {
    //01 05 00 00 FF 00 8C 3A
    public final static byte[] ONE_ONE= {(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x00,(byte)0xff,(byte)0x00,(byte)0x8c,(byte)0x3a};
    //01 05 00 01 FF 00 DD FA
    public final static byte[] ONE_TWO= {(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x01,(byte)0xff,(byte)0x00,(byte)0xdd,(byte)0xfa};
    //01 05 00 02 FF 00 2D FA
    public final static byte[] ONE_THREE= {(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x02,(byte)0xff,(byte)0x00,(byte)0x2d,(byte)0xfa};
    //01 05 00 03 FF 00 7C 3A
    public final static byte[] ONE_FOUR= {(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x03,(byte)0xff,(byte)0x00,(byte)0x7c,(byte)0x3a};
    //01 05 00 04 FF 00 CD FB
    public final static byte[] ONE_FIVE= {(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x04,(byte)0xff,(byte)0x00,(byte)0xcd,(byte)0xfb};
    //01 05 00 05 FF 00 9C 3B
    public final static byte[] ONE_SIX= {(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x05,(byte)0xff,(byte)0x00,(byte)0x9c,(byte)0x3b};
    //01 05 00 06 FF 00 6C 3B
    public final static byte[] ONE_SEVEN= {(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x06,(byte)0xff,(byte)0x00,(byte)0x6c,(byte)0x3b};
    //01 05 00 07 FF 00 3D FB
    public final static byte[] ONE_EHGIT= {(byte)0x01,(byte)0x05,(byte)0x00,(byte)0x07,(byte)0xff,(byte)0x00,(byte)0x3d,(byte)0xfb};


    //02 05 00 00 FF 00 8C 09
    public final static byte[] TWO_ONE= {(byte)0x02,(byte)0x05,(byte)0x00,(byte)0x00,(byte)0xff,(byte)0x00,(byte)0x8c,(byte)0x09};
    //02 05 00 01 FF 00 DD C9
    public final static byte[] TWO_TWO= {(byte)0x02,(byte)0x05,(byte)0x00,(byte)0x01,(byte)0xff,(byte)0x00,(byte)0xdd,(byte)0xc9};
    //02 05 00 02 FF 00 2D C9
    public final static byte[] TWO_THREE= {(byte)0x02,(byte)0x05,(byte)0x00,(byte)0x02,(byte)0xff,(byte)0x00,(byte)0x2d,(byte)0xc9};
    //02 05 00 03 FF 00 7C 09
    public final static byte[] TWO_FOUR= {(byte)0x02,(byte)0x05,(byte)0x00,(byte)0x03,(byte)0xff,(byte)0x00,(byte)0x7c,(byte)0x09};
    //02 05 00 04 FF 00 CD C8
    public final static byte[] TWO_FIVE= {(byte)0x02,(byte)0x05,(byte)0x00,(byte)0x04,(byte)0xff,(byte)0x00,(byte)0xcd,(byte)0xc8};
    //02 05 00 05 FF 00 9C 08
    public final static byte[] TWO_SIX= {(byte)0x02,(byte)0x05,(byte)0x00,(byte)0x05,(byte)0xff,(byte)0x00,(byte)0x9c,(byte)0x08};
    //02 05 00 06 FF 00 6C 08
    public final static byte[] TWO_SEVEN= {(byte)0x02,(byte)0x05,(byte)0x00,(byte)0x06,(byte)0xff,(byte)0x00,(byte)0x6c,(byte)0x08};
    //02 05 00 07 FF 00 3D C8
    public final static byte[] TWO_EHGIT= {(byte)0x02,(byte)0x05,(byte)0x00,(byte)0x07,(byte)0xff,(byte)0x00,(byte)0x3d,(byte)0xc8};

    //FE 0F 00 00 00 08 01 00 B1 91
    public final static byte[] ALL_CLOSE= {(byte)0xfe,(byte)0x0f,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x01,(byte)0x00,(byte)0xb1,(byte)0x91};


    public static int ISO_VALUE = 250;
    public static int flag = 0;

}
