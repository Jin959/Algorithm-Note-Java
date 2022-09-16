// boj-1260 https://www.acmicpc.net/problem/1260 

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

public class BFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, m;
	static int start;
	
	static int[] used;
	static ArrayList<Integer>[] adj;
	
	static Queue<Integer> q;
	
	static void bfs(int s) {
		// 0. 시작점 세팅
		q.add(s);
		used[s] = 1;
		
		while(!q.isEmpty()) {
			// 1. 노드를 하나 택하고 pop한다.
			int now = q.poll();
			
			// 전위 순회
			System.out.print(now + " ");
			
			// 2. 연결된 노드 탐색
			for (int next : adj[now]) {
				
				// Cycle check & 가지치기
				if (used[next] == 1) continue;
				
				// 기록
				used[next] = 1;
				
				q.add(next);
				
				// backtracking : 다양한 경로를 탐색하는 것이 아니라서 이 문제의 경우 필요없다.
				// used[next] = 0;
			}
		}
		
		return;
	}

	public static void main(String[] args) throws IOException {
		// input
		st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		// 배열 할당 & init
		used = new int[n + 1];
		adj = new ArrayList[n + 1];
		
		for (int i = 1; i < n + 1; i++) adj[i] = new ArrayList<>();
		
		// 간선정보 input
		int from, to;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			// 양방향 간선
			adj[from].add(to);
			adj[to].add(from);
		}
		
		// 문제조건 : 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
		// line 65 에서 adj[0]의 메모리를 할당하지 않았다.
		for (ArrayList<Integer> ele : adj) if(ele != null) Collections.sort(ele); 
		
		q = new LinkedList<>();
		bfs(start);
	}

}
