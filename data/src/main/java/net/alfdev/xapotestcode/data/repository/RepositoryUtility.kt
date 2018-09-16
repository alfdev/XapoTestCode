package net.alfdev.xapotestcode.data.repository

import java.util.Locale

object RepositoryUtility {
    fun formatQueryStringForProjects(pageSize: Int, nextPageToken: String?): String {
        val token_string = if (nextPageToken.isNullOrEmpty()) null else "\"$nextPageToken\""

        return """query {
  search(type: REPOSITORY, query: "topic:android sort:stars-desc", first:$pageSize, after:$token_string) {
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
    }

    fun formatQueryStringForProjectDetail(owner: String, name: String): String {
        val formatted_owner = "\"$owner\""
        val formatted_name = "\"$name\""

        return """query {
  repository(owner:$formatted_owner, name:$formatted_name) {
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

    issues {
      totalCount
    }

    pullRequests {
      totalCount
    }

    stargazers {
      totalCount
    }

    watchers {
      totalCount
    }

    releases {
      totalCount
    }

    commitComments {
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
}"""
    }
}