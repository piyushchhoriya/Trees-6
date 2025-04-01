## Problem2: Vertical order tree traversal https://leetcode.com/problems/binary-tree-vertical-order-traversal/

If we use DFS then we have to maintaion a level and still we wont get answer in expected sequence inside a list of list.
So we will go with BFS here and if we go left node of the root node then we will decrement the distance and if we go to right then we will add 1 to the distance
Also we will maintain a HashMap in which we will store the key as the distance and value as a list of nodes.
Now if we want the lists in sorted order of distances and hashmap doesnot store in sorted order so we will keep min and max variable 
and then we can iterate over it from min to max

Time Complexity : O(n)
Space Complexity : O(n)

public List<List<Integer>> verticalOrder(TreeNode root){
    //base case
    if(root == null){
        return new ArrayList<>();
    }
    //Queues one to store nodes and other for distance
    Queue<TreeNode> q = new LiskedList<>();
    Queue<Integer> d = new LiskedList<>();
    List<List<Integer>> result = new ArrayList<>();
    HashMap<Integer,List<Integer>> map = new HashMap<>();
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    q.add(root);
    d.add(0);
    while(!q.isEmpty()){
       TreeNode curr = q.poll();
       int dis = d.poll();
       min = Math.min(min,dis);
       max = Math.max(max,dis);
       if(!map.containsKey(dis)){
        map.put(dis, new ArrayList<>());
       }
       map.get(dis).add(curr);
       if(curr.left!=null){
            q.add(curr.left);
            d.add(dis-1);
       }
       if(curr.right!=null){
            q.add(curr.right);
            d.add(dis+1);
       }

    }
    for(int i=min;i<=max;i++){
        result.add(map.get(i));
    }

    return result;
}