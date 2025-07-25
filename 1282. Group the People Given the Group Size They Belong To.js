function groupThePeople(groupSizes) {
    const groups = new Map();
    const result = [];

    for (let i = 0; i < groupSizes.length; i++) {
        const size = groupSizes[i];

        if (!groups.has(size)) {
            groups.set(size, []);
        }

        groups.get(size).push(i);

        if (groups.get(size).length === size) {
            result.push(groups.get(size));
            groups.set(size, []); // Reset group for next batch
        }
    }

    return result;
}
