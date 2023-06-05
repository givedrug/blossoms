interface UrlBlock {
    url_name: string,
    url_link: string,
    url_count?: number
}

interface UrlList {
    [index: number]: UrlBlock
}

interface TypeBlock {
    type_name: string
    url_list: UrlList
}

interface UrlJson {
    [index: number]: TypeBlock
}