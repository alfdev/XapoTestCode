package net.alfdev.xapotestcode.data.repository

val QUERY_PROJECTS_LIST = """query {
  search(type: REPOSITORY, query: "topic:android sort:stars-desc", first:%1, after:%2) {
    repositoryCount
    pageInfo {
      endCursor
      startCursor
    }

    nodes {
      ... on Repository {
          id
          name
          nameWithOwner
          description
          url
          updatedAt
          forkCount

        	owner {
            login
            avatarUrl
          }

          stargazers {
            totalCount
          }

          repositoryTopics(first:5) {
            totalCount
            nodes {
              resourcePath
            }
          }

          languages(first:1, orderBy: {field:SIZE, direction:DESC}) {
            totalCount
            nodes {
              name
              color
            }
          }
      }
    }
  }
}"""

val QUERY_PROJECT_DETAIL = ""