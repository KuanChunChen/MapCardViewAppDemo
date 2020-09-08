package k.c.mapcardviewdemo.app.model.enum

enum class GISScenarioType(val value: Int) {
    /***
     * 1=上車,
     * 2=下車,
     * 3=我的最愛新增,
     * 4=我的最愛編輯,
     * 5=新增停靠點,
     * 6=新增下車點,
     * 7=修改下車點,
     * 8=修改停靠點
     */


    PICK_UP(1), DROP_OFF(2), FAVORITE_ADD(3), FAVORITE_EDIT(4), STOP_ADD(5),
    DROP_OFF_ADD(6), DROP_OFF_EDIT(7), STOP_EDIT(8);


    companion object {

        fun valueOf(value: Int): GISScenarioType? {
            return when (value) {
                1 -> PICK_UP
                2 -> DROP_OFF
                3 -> FAVORITE_ADD
                4 -> FAVORITE_EDIT
                5 -> STOP_ADD
                6 -> DROP_OFF_ADD
                7 -> DROP_OFF_EDIT
                8 -> STOP_EDIT
                else -> null
            }
        }
    }
}