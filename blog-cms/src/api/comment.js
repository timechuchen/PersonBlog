import axios from '@/utils/request'

export function getCommentListByQuery() {
    return axios({
        url: 'comments',
        method: 'GET',
    })
}

export function getBlogList() {
    return axios({
        url: 'blogIdAndTitle',
        method: 'GET'
    })
}

export function updatePublished(id, isPublished) {
    return axios({
        url: 'comment/published',
        method: 'PUT',
        params: {
            id,
            isPublished
        }
    })
}

export function deleteCommentById(id) {
    return axios({
        url: 'comment',
        method: 'DELETE',
        params: {
            id
        }
    })
}

export function editComment(form) {
    return axios({
        url: 'comment',
        method: 'PUT',
        data: {
            ...form
        }
    })
}