# silenium adalah tools auto testing yang digunakan untuk aplikasi web, dan disini saya memakai silenium jenis webdriver
from selenium import webdriver
# bs4 atau Beautiful Soup adalah library yang berfungsi untuk menarik data html dan xml. biasanya library ini digunakan untuk web scraping
from bs4 import BeautifulSoup


def main():
    # execute path dari webdriver chrome
    driver = webdriver.Chrome(executable_path='drivers/chromedriver.exe')
    # mengambil url dari youtube tranding agar webdriver membuka url tersebut
    driver.get(
        'https://www.youtube.com/feed/trending?bp=6gQJRkVleHBsb3Jl')
    content = driver.page_source.encode('utf-8').strip()
    soup = BeautifulSoup(content, 'lxml')
    # jadi bentuk umum dari kode dibawah "...findAll('tag', 'class atau id')"
    judul = soup.findAll('a', id='video-title')
    views = soup.findAll('span', class_='style-scope ytd-video-meta-block')
    url_video = soup.findAll('a', id='video-title')
    channel = soup.findAll(
        'a', class_='yt-simple-endpoint style-scope yt-formatted-string')
    deskripsi = soup.findAll('yt-formatted-string', id='description-text')
    i = 0  # untuk data views dan waktu nya
    j = 0  # untuk data si url_video
    k = 0  # untuk data channel
    l = 0  # untuk data deskripsi
    for title in judul:
        print('\n\n\Judul : {}\nChannel : {}\n Views : {}\n Time : {}\nDeskripsi : {}\nUrl : https://www.youtube.com{}'.format(title.text, channel[k].text,
                                                                                                                               views[i].text, views[i+1].text, deskripsi[l].text, url_video[j].get('href')))
        i += 2
        j += 1
        k += 2
        l += 1


main()
