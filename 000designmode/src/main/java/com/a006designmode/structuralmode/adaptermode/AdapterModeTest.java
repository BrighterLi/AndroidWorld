package com.a006designmode.structuralmode.adaptermode;

import com.a006designmode.structuralmode.adaptermode.gb.GBSocket;
import com.a006designmode.structuralmode.adaptermode.gb.GBSocketInterface;

//一个示例让你明白适配器模式:https://blog.csdn.net/opening2000/article/details/18797005?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-10&spm=1001.2101.3001.4242
public class AdapterModeTest {

    /**
    * 代码中有两个接口，分别为德标接口和国标接口，分别命名为DBSocketInterface和GBSocketInterface，
     * 此外还有两个实现类，分别为德国插座和中国插座，分别为DBSocket和GBSocket。为了提供两套接口之间的适配，
     * 我们提供了一个适配器，叫做SocketAdapter。除此之外，还有一个客户端，比如是我们去德国旅游时住的一家宾馆，
     * 叫Hotel，在这个德国旅馆中使用德国接口。
    * */

    public static void test() {
        //去德国旅游，带去的充电器是国标的（可以将这里的GBSocket看成是充电器）
        GBSocketInterface gbSocket = new GBSocket();
        //来到德国后， 找到一家德国宾馆住下 (这个宾馆使用的依然是德国标准的插口)
        Hotel hotel = new Hotel();
        //由于没法充电，我拿出随身带去的适配器，并且将我带来的充电器插在适配器的上端插孔中。这个上端插孔是符合国标的，我的充电器完全可以插进去
        SocketAdapter socketAdapter = new SocketAdapter(gbSocket);
        //再将适配器的下端插入宾馆里的插座上
        hotel.setSocket(socketAdapter);
        //可以在宾馆中使用适配器进行充电了
        hotel.charge();
    }
}
