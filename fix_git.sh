#!/bin/bash
cd "c:\Users\sapko\Desktop\Complaint-System"
export FILTER_BRANCH_SQUELCH_WARNING=1

git filter-branch -f --env-filter '
if [ "$GIT_COMMITTER_EMAIL" = "rishabhsingh971@gmail.com" ] || [ "$GIT_AUTHOR_EMAIL" = "rishabhsingh971@gmail.com" ] || [ "$GIT_COMMITTER_EMAIL" = "rishabh.singh@smartprix.com" ] || [ "$GIT_AUTHOR_EMAIL" = "rishabh.singh@smartprix.com" ]; then
  export GIT_COMMITTER_NAME="Sandesh Sapkota"
  export GIT_COMMITTER_EMAIL="sapkotasandeshlive12@gmail.com"
  export GIT_AUTHOR_NAME="Sandesh Sapkota"
  export GIT_AUTHOR_EMAIL="sapkotasandeshlive12@gmail.com"
fi
' -- --all
