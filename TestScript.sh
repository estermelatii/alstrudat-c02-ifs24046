#!/bin/bash

echo "[INFO] ================================================="
echo "[INFO] Java Weighted Test Cases"
echo "[INFO] ================================================="

mvn clean package -q
if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi
echo "Build successful!"

TOTAL_SCORE=0
ANY_FAILED=0
WEIGHTS_FILE="testcases/weights.txt"
mapfile -t WEIGHTS < "$WEIGHTS_FILE"

NUM_TC=${#WEIGHTS[@]}

for i in $(seq 1 $NUM_TC); do
    IDX=$((i - 1))
    WEIGHT=${WEIGHTS[$IDX]}
    INPUT="testcases/input${i}.txt"
    EXPECTED="testcases/expected${i}.txt"

    echo "|---------------------------------------------------|"
    echo "Test Case $i (Weight: ${WEIGHT}%)..."

    ACTUAL=$(java -cp target/*.jar del.alstrudat.App < "$INPUT" 2>/dev/null)
    EXPECTED_CONTENT=$(cat "$EXPECTED")

    echo "  Actual Output (testcase $i):"
    echo "$ACTUAL"
    echo "  Expected Output (testcase $i):"
    echo "$EXPECTED_CONTENT"

    if [ "$ACTUAL" = "$EXPECTED_CONTENT" ]; then
        echo "  Test case $i passed!"
        TOTAL_SCORE=$((TOTAL_SCORE + WEIGHT))
    else
        echo "X Test case $i failed!"
        ANY_FAILED=1
    fi
done

echo "|---------------------------------------------------|"
echo "  Final Score: ${TOTAL_SCORE}%"

if [ $ANY_FAILED -eq 1 ]; then
    echo "X Error: Some test cases failed! Exiting with error."
    echo "Error: Process completed with exit code 1."
    exit 1
fi

echo "All test cases passed!"
exit 0
