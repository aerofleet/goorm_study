import { useState } from "react";


export default function LightSwitch() {
  
  const [isLight, setIsLight] = useState(true);

  function handleClick() {
    const nextLightState = !isLight;
    setIsLight(nextLightState);

    document.body.style.backgroundColor = nextLightState ? 'black' : 'white';
    } 

  return (
    <button onClick={handleClick}>
      {isLight ? '끄기' : '켜기'} (현재: {isLight ? '밝음' : '어두움'})
    </button>
  );
}
