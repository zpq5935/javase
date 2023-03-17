package interest.japan;

public enum WordDataBase {
    a('あ', " a"), i('い', " i"), u('う', " u"), e('え', " e"), o('お', " o"),
    ka('か', "ka"), ki('き', "ki"), ku('く', "ku"), ke('け', "ke"), ko('こ', "ko"),
    sa('さ', "sa"), si('し', "shi"), su('す', "su"), se('せ', "se"), so('そ', "so"),
    ta('た', "ta"), ti('ち', "chi"), tu('つ', "tsu"), te('て', "te"), to('と', "to"),
    na('な', "na"), ni('に', "ni"), nu('ぬ', "nu"), ne('ね', "ne"), no('の', "no"),
    ha('は', "ha"), hi('ひ', "hi"), hu('ふ', "fu"), he('へ', "he"), ho('ほ', "ho"),
    ma('ま', "ma"), mi('み', "mi"), mu('む', "mu"), me('め', "me"), mo('も', "mo"),
    //
    ya('や', "ya"), yu('よ', "yu"), yo('よ', "yo"),
    ra('ら', "ra"), ri('り', "ri"), ru('る', "ru"), re('れ', "re"), ro('ろ', "ro"),
    wa('わ', "wa"), wo('を', "wo"),
    n('ん', "n,");

    private Character chr;
    private String pronounce;

    WordDataBase(Character chr, String pronounce) {
        this.chr = chr;
        this.pronounce = pronounce;
    }
}
