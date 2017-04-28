#!/usr/bin/env python3

# Usage:
# ./validator INFILE < contestant_output > score.txt
import argparse, sys
from sys import stdin, exit

try:
    parser = argparse.ArgumentParser(description="Validator for friends2")
    parser.add_argument('infile', metavar='I', type=str, help='Input file')
    parser.add_argument('ansfile', metavar='A', type=str, help='Judge file')
    parser.add_argument('fbdir', metavar='F', type=str, help='Feedback directory')
    args = parser.parse_args()

    def die(msg):
        safe_print(msg)
        # f = open(args.fbdir + os.sep + "score.txt", "wt+", encoding="utf-8")
        # f.write("WA 0")
        # f.close()
        exit(43)

    def accept(msg):
        safe_print(msg)
        # with f as open(args.fbdir + os.sep + "score.txt", "wt+", encoding="utf-8"):
            # f.write("AC 1")
        # with f as open(args.fbdir + os.sep + "judgemessage.txt", "wt+", encoding="utf-8"):
            # f.write("Correct")
        exit(42)

    def safe_print(n):
        try:
            print(n)
            sys.stdout.flush()
        except IOError:
            pass

    def getint(word):
        try:
            num = int(word)
            return num
        except:
            die("Expected integer, got: {}".format(word))

    def grade():
        # Step 0:
        # Read input file
        with open(args.infile, "r", encoding="utf-8") as f:
            n, p, q = map(int, f.readline().split())
            graph = []
            for i in range(n):
                graph.append(list(map(int, f.readline().split()[1:])))


        with open(args.ansfile, "r", encoding='utf-8') as f:
            hasSolution = f.readline().strip() == "home"

        asymmetric = False
        hased = set()
        for i in range(n):
            for j in graph[i]:
                hased.add((i, j))
        for i in range(n):
            for j in graph[i]:
                if not (j, i) in hased:
                    asymmetric = True

        # Step 1: Accept if correctly judged detetion
        line1 = stdin.readline().strip().lower()
        if not hasSolution:
            if line1 == "detention":
                accept("Detention accept")
            elif line1 != "home":
                die("Printed something other than home or detention: {}".format(line1))
        else:
            if line1 != "home":
                die("Judge claims solution exists, but team printed {}".format(line1))

        assert line1 == "home" 

        line2 = stdin.readline().strip()
        # Step 3: Check first line is an integer (since it is not "detention")
        partitions_count = getint(line2)
        if not (1 <= partitions_count <= n):
            die("# of groups {}, but {} nodes".format(groups, n))

        seen = [-1] * n
        partitions = []
        for i in range(partitions_count):
            line = stdin.readline().split()
            if not line:
                die("empty line")
            if not (1 <= getint(line[0]) <= p):
                die("Group {} size={}, but p={}".format(i, getint(line[0]), p))

            s = set(map(getint, line[1:]))
            if len(s) != len(line)-1:
                die("Duplicate group members")
            partitions.append(s)
            if len(s) != getint(line[0]):
                die("Expected {} group members in {}, found {}".format(getint(line[0]), i, len(s)))
            for elm in s:
                if not (0 <= elm < n):
                    die("Student {} in partition {} does not exist".format(elm, i))
                if seen[elm] >= 0:
                    die("Groups {} and {} both contain {}".format(seen[elm], i, elm))
                seen[elm] = i

        for line in stdin.readline():
            if len(line.strip()) > 0:
                die("Didn't expect more than {} groups, but found {}".format(partitions_count, line))

        for i in range(n):
            if seen[i] < 0:
                die("Student {} not in any partition".format(i))

        for i, partition in enumerate(partitions):
            outedges = 0
            for member in partition:
                for friend in graph[member]:
                    if friend not in partition:
                        outedges += 1
                        if outedges > q:
                            die("Partition {} has > {}=q edges".format(i, q))

        # Step 4: Judge error if incorrectly judged a possible partition
        if not hasSolution:
            if asymmetric:
                die("should have found asymmetry")
            safe_print("Judge claims no solution exits, but you say there is; however you got so far validated. Weird.")
            exit(1)

        accept("Validated!")

    grade()

except UnicodeDecodeError as e:
    die("not utf-8")
