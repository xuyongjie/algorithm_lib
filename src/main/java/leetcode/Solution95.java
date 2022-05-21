package leetcode;

import com.yon.algorithm.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution95 {

    public static void main(String[] args) {
        Solution95 solution95 = new Solution95();
        solution95.generateTrees(3);
    }

    List<TreeNode> result;

    public List<TreeNode> generateTrees(int n) {
        result = new ArrayList<>();
        TreeNode path = new TreeNode();
        generate(n, 0, 1, n, path, path);
        return result;
    }

    private void generate(int n, int index, int from, int to, TreeNode path, TreeNode pre) {
        if (index == n) {
            result.add(path);
            return;
        }
        if (from == to) {
            pre.val = from;
            pre.left = null;
            pre.right = null;
            return;
        }
        if (from > to) {
            pre = null;
            return;
        }
        for (int i = from; i <= to; i++) {
            pre.val = i;
            pre.left = new TreeNode();
            pre.right = new TreeNode();
            generate(n, index + 1, from, i - 1, path, pre.left);
            generate(n, index + 1, i + 1, to, path, pre.right);
        }
    }
}
