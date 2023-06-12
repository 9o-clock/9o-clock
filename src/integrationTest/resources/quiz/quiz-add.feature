@h2
Feature: 퀴즈 추가

  Scenario: 퀴즈 추가 및 저장
#    관리자 추가 시 Given에 관리자 설정 (관리자 출현 안할거같은데 ㅎㅎ...)
    When 퀴즈 추가 요청
      | title     | 반려동물                    |
      | content   | 반려동물로 선택하고 싶은 것은 무엇인가요? |
      | choices   | 강아지, 고양이, 토끼, 람쥐      |
      | releaseAt | D+5 14:00:00                     |

    Then 퀴즈 저장됨
      | id | title | content                 | release_at | created_at | updated_at |
      | 1  | 반려동물  | 반려동물로 선택하고 싶은 것은 무엇인가요? | D+5 14:00:00    | now        | now        |

    Then 퀴즈 선택지 저장됨
      | id | quiz_id | text |
      | 1  | 1       | 강아지  |
      | 2  | 1       | 고양이  |
      | 3  | 1       | 토끼   |
      | 4  | 1       | 람쥐   |
