function solution(n, computers) {
    let networks = [];
    
    computers.forEach((arr, i) => {
        let temp_net = [];
        arr.forEach((bool, index) => {
            if (bool) {
                temp_net.push(index);
            }
        });
        networks.push(temp_net);
    });

    let big_nets = [];
    let visited_items = new Set();

    for (let i = 0; i < n; i++) {
        if (visited_items.has(i)) {
            continue;
        }

        const makeBigNet = (network, networks, visited) => {
            let bigSet = new Set();

            const dfs = (node) => {
                if (visited.has(node)) return;
                visited.add(node);
                bigSet.add(node);

                networks[node].forEach((neighbor) => {
                    if (!visited.has(neighbor)) {
                        dfs(neighbor);
                    }
                });
            };

            network.forEach((node) => dfs(node));
            return bigSet;
        };

        let big_net = makeBigNet(networks[i], networks, visited_items);
        big_nets.push(big_net);
    }
    return big_nets.length;
}
