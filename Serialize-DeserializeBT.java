## Problem2: Serialize and Deserialize Binary Tree (https://leetcode.com/problems/serialize-and-deserialize-binary-tree)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

In this question we have to serialize (Convert a tree to a string) & Deserialize (Convert a string back to a Tree)
So we have to write a functions for both
Serialize : We will use a queue and we will do a BFS traversal. Also we will add null values to the queue.
So the process will be we will add a root first and start a loop. so we will poll a node and explore its childrens if they are null still we will add them in a queue.
Also we will maintain a StringBuilder to which we will add the polled values and seperate them with , 
So if the polled value is not null then only we will explore its childerens else not
At the end we will convert a StringBuilder to a String and then return

Deserialize:
In this we will convert a String to a String array and also we will maintain a queue and one variable which will start from index 1.
So now here the challenge is how to identify any value is children of other node.
So what we will do is we will take Oth index value create a node of it and add it in a queue.
Now as we are maintaining i and we know that first index will always be the left node of the root also it can also be null.
So we will create a node of it if it is not null and attach to a root. Also we will add it in a queue. we will increment i 
So for each node we pop we will see the left and right and increment i

Time Complexity : O(n)
Space Complexity : O(n)

public class Codec {
    // Encodes a tree to a single string.
    //Serialize
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if(curr!=null){
                sb.append(curr.val);
                q.add(curr.left);
                q.add(curr.right);
            }
            else{
                sb.append("null");
            }
            sb.append(",");
            
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       if(data == null || data.length()==0){
        return null;
       } 
       System.out.println(data);
       String[] strArray = data.split(",");
       Queue<TreeNode> q = new LinkedList<>();
       TreeNode root = new TreeNode(Integer.parseInt(strArray[0]));
       q.add(root);
       int i = 1;
       while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if(!strArray[i].equals("null")){
                curr.left = new TreeNode(Integer.parseInt(strArray[i]));
                q.add(curr.left);
            }
            i++;
            if(!strArray[i].equals("null")){
                curr.right = new TreeNode(Integer.parseInt(strArray[i]));
                q.add(curr.right);
            }
            i++;
       }
       return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));