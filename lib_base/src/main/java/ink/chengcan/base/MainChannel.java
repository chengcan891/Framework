package ink.chengcan.base;

/**
 * Demo class
 *
 * @author hp
 * @date 2019/10/18
 */
public enum MainChannel {
    HOME(0, "HOME"), VIEW(1, "VIEW"), ME(2, "ME");
    public int id;
    public String name;

    MainChannel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
