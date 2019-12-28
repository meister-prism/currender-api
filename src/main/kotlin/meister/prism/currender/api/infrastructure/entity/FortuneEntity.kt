package meister.prism.currender.api.infrastructure.entity

class FortuneEntity (
        val horoscopes: ArrayList<Horoscope>,
        val date: String // yyyy-MM-dd
) {
    class Horoscope(
            val sign: String, // 星座名
            val rank: String, // 順位
            val money: String, // 金運(1-5)
            val total: String, // 総合運(1-5)
            val job: String, // 仕事運(1-5)
            val love: String, // 恋愛運(1-5)
            val color: String, // ラッキーカラー
            val content: String // 占い内容
    )
}


/**
 * content: "忙しい一日になりそう。疲れたら、目を閉じてリラックス。頭の疲れが取れて、気分も変わるでしょう。人の話はよく聞いて。",
item: "エクレア",
money: 4,
total: 3,
job: 3,
color: "オレンジ",
day: "",
love: 4,
rank: 6,
sign: "牡牛座"
 */