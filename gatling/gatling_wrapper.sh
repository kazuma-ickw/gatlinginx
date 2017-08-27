#!/bin/bash

readonly OUTPUT_PATH=/tmp/tmp.txt

if [ -e ${OUTPUT_PATH} ]; then
  rm ${OUTPUT_PATH}
fi

gatling.sh | tee ${OUTPUT_PATH}
output=`tail -1 ${OUTPUT_PATH}`

if ! echo "$output" | grep -q "Please open the following file"; then
  cat ${OUTPUT_PATH}
  echo -e '\n\nerror occurred. please fix above error.'
  exit 1
fi

echo -e "\n\n$output" | sed -e "s~/opt/gatling~http://localhost:8080~" | sed -e "s/following file/following URL/"
