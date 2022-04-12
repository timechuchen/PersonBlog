import axios from '@/utils/request'

export function updateSite(hotTag) {
    return axios({
        url: '/page/updateSite',
        method: 'put',
        data: {
            hotTag,
        }
    })
}

export function getAllSite() {
    return axios({
        url: '/page/getAllSite',
        method: 'get'
    })
}