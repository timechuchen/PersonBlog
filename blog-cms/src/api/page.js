import axios from '@/utils/request'

export function updateSite(hotTag) {
    return axios({
        url: '/page/updateSite',
        method: 'post',
        data: {
            hotTag,
        }
    })
}

export function getAllSite() {
    return axios({
        url: '/page/getAllSite',
        method: 'post'
    })
}