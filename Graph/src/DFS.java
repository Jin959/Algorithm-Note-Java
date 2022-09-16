// boj-1260 https://www.acmicpc.net/problem/1260 

/*
 * Tree vs. Graph
 
 Tree is one-way, has "child" or "parent". That is, Tree is Graph that doesn't have "Cycle".

 따라서 Graph에서는 Cycle(순환여부)를 고려해야한다. -> used(visited) 배열을 사용한다.
 
 */

/*
n <= 1000; // # of Nodes
m <= 10000; // # of Edges
*/
import java.io.*;
import java.util.*;

public class DFS {	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, m; // # of nodes & edges
	static int start;
	static int[] used; // Cycle을 돌지 않도록
	
	static ArrayList<Integer>[] adj; // adjacency list; ArrayList로 된 배열
		
	static void dfs(int now) {
		// 1. base condition : 이 문제는 목적지(종료조건)가 없는 모든 노드 탐색이 목적이므로 기저조건은 없다.
		//    단지 방문 노드만 print, 여기서 print하는 것을 전위 순회 라고한다.
		System.out.print(now + " ");
		
		// 2. 연결된 노드 탐색
		for (int next : adj[now]) {
			// Cycle Check 및 Pruning
			if (used[next] == 1) continue;
			
			// 기록
			used[next] = 1;
			
			dfs(next);
			
			// backtracking : 다양한 경로를 탐색하는 것이 아니라서 이 문제의 경우 필요없다.
			// used[next] = 0;
		}
		
		return;
	}
	
	// 입출력예외를 나중으로 미룬다.
	public static void main(String[] args) throws IOException {
		// input
		// BufferedReader.readLine() ; Enter이전의 입력을 String으로 모두 받는다.
		// StringTokenizer(String str, delim) ; delim(구분자, default는 공백(' '))를
		// 			기준으로 나누어 스트링을 토크나이징(Parsing)하여 StringTokenizer 객체 생성
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		// 정점 번호는 1번부터 N번까지이다.
		used = new int[n + 1]; // 0L로 초기화
		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
		
		int from, to;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine()); // 새로운 줄 마다 읽어줘야한다.
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			// bidirected(양방향) graph
			adj[from].add(to);
			adj[to].add(from);
			
		}
		
		// 문제조건 : 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
		// line 65 에서 adj[0]의 메모리를 할당하지 않았다.
		for (ArrayList<Integer> ele : adj) if(ele != null) Collections.sort(ele); 
	
		used[start] = 1;
		dfs(start);
		
	}

}
