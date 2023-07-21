package leetcode._100;

import leetcode.LeetCodeConstant;

import java.util.HashSet;


/**
 * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">第3题 无重复字符的最长子串</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.String,
        LeetCodeConstant.TagEnum.Hash,
        LeetCodeConstant.TagEnum.SlidingWindow
})
public class _003SubstrNorepeatMaxlength {

    public static void main(String[] args) {
        System.out.println(new _003SubstrNorepeatMaxlength().lengthOfLongestSubstring("abcabcbb"));
    }


    /**
     * 左右指针，提前判断，重复时只是移动左指针并不移除（全部重复值及其左侧数据）
     */
    /**
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     */
    public int lengthOfLongestSubstring(String s) {// todo
        HashSet<Character> indexAndCharacter = new HashSet<>();
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
        }
        return maxLength;
    }


}
