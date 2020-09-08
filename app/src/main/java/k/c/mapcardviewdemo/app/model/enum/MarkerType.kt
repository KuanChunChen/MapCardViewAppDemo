package k.c.mapcardviewdemo.app.model.enum

enum class MarkerType(val value: Int) {
    COMMON(1), STORE(2), FAVORITE(3);

    companion object {
        fun valueOf(value: Int): MarkerType? {
            when (value) {
                1 -> return COMMON
                2 -> return STORE
                3 -> return FAVORITE
            }
            return null
        }
    }
}

