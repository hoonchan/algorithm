import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final long LIMIT = 1_000_000_000L;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken()); // 사람 수
            int C = Integer.parseInt(st.nextToken()); // 봉지당 사탕 수

            // 예외 처리
            if (C == 1) {
                // C가 1이면 K*X + 1 = Y → Y = K*X + 1 → Y > K 이므로 Y는 무조건 존재
                if (K + 1 > LIMIT) {
                    sb.append("IMPOSSIBLE\n");
                } else {
                    sb.append(K + 1).append("\n");
                }
                continue;
            }

            if (K == 1) {
                // K가 1이면 X + 1 = C*Y → C*Y = X + 1 → 해는 항상 존재
                sb.append(1).append("\n"); // X=0 → Y=1
                continue;
            }

            long[] egcd = extendedGCD(K, C); // Kx + Cy = 1
            long g = egcd[0], x = egcd[1], y = egcd[2];

            if (g != 1) {
                sb.append("IMPOSSIBLE\n"); // 해가 없음
                continue;
            }

            // y: 봉지 개수
            // 일반해: y = y0 + k*K
            long y0 = y;

            // y > 0으로 보정
            long k = (long) Math.ceil((1.0 - y0) / K);
            y0 += K * k;

            if (y0 > LIMIT) {
                sb.append("IMPOSSIBLE\n");
            } else {
                sb.append(y0).append("\n");
            }
        }

        System.out.print(sb.toString());
    }

    // 확장 유클리드 알고리즘
    // 반환: [gcd, x, y] → ax + by = gcd(a, b)
    private static long[] extendedGCD(long a, long b) {
        long s0 = 1, s1 = 0;
        long t0 = 0, t1 = 1;
        while (b != 0) {
            long q = a / b;
            long r = a % b;

            long tmpS = s0 - q * s1;
            long tmpT = t0 - q * t1;

            a = b;
            b = r;
            s0 = s1;
            s1 = tmpS;
            t0 = t1;
            t1 = tmpT;
        }
        return new long[]{a, s0, t0};
    }
}
