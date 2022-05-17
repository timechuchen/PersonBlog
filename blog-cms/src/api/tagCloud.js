import axios from '@/utils/request'

export function getAllTagCloud() {
    return axios({
        url: '/tagClouds',
        method: 'GET',
    })
}

export function addTagCloud(tagCloud) {
    return axios({
        url: '/tagCloud',
        method: 'POST',
        data: {
            ...tagCloud
        }
    })
}

export function updateTagCloud(tagCloud) {
    return axios({
        url: '/tagCloud',
        method: 'PUT',
        data: {
            ...tagCloud
        }
    })
}

export function deleteTagCloud(id) {
    return axios({
        url: '/tagCloud',
        method: 'DELETE',
        params: {
            id
        }
    })
}